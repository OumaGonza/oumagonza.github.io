#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import quickfix as fix


class MyApplication(fix.Application):
    def onCreate(self, sessionID):
        print(f"onCreate: {sessionID}")
        return

    def onLogout(self, sessionID):
        print(f"onLogout: {sessionID}")

    # Print the session status
        print(f"Session Status: {fix.Session.lookupSession(sessionID).getSessionState()}")
        return


    def onLogout(self, sessionID):
        print(f"onLogout: {sessionID}")
        return

    def toAdmin(self, message, sessionID):
        print(f"toAdmin: {sessionID}")
        return

    def fromAdmin(self, message, sessionID):
        print(f"fromAdmin: {sessionID}")
        print(f"fromApp: {sessionID}")
        print(f"Received message: {message}")
        return

    def toApp(self, message, sessionID):
        print(f"toApp: {sessionID}")
        # Implement logic to send messages to the counterparty
        self.sendNewOrderSingle(sessionID)
        return

    def fromApp(self, message, sessionID):
        print(f"fromApp: {sessionID}")
        # Implement logic to process received messages
        self.processMessage(message)
        return

    def sendNewOrderSingle(self, sessionID):
    # Create a NewOrderSingle message
        newOrderSingle = fix.Message()

    # Set required fields
        header = newOrderSingle.getHeader()
        header.setField(fix.MsgType("D"))  # D is the MsgType for NewOrderSingle
        newOrderSingle.setField(fix.ClOrdID("12345"))  # Replace with your ClOrdID
        newOrderSingle.setField(fix.Symbol("AAPL"))  # Replace with the desired symbol
        newOrderSingle.setField(fix.Side(fix.Side_BUY))  # or fix.Side_SELL
        newOrderSingle.setField(fix.OrdType(fix.OrdType_LIMIT))  # or fix.OrdType_MARKET

    # Add more fields as needed

    # Send the message
        fix.Session.sendToTarget(newOrderSingle, sessionID)


    def processMessage(self, message):
        msgType = fix.MsgType()
        message.getHeader().getField(msgType)

        if msgType.getValue() == '6':  # 'IOI' message type
            ioiId = fix.IOIid()
            transType = fix.IOITransType()
            symbol = fix.Symbol()
            side = fix.Side()
            ioiShares = fix.IOIShares()

            message.getField(ioiId)
            message.getField(transType)
            message.getField(symbol)
            message.getField(side)
            message.getField(ioiShares)

            # Print relevant information
            print(f"Received IOI message - ID: {ioiId.getValue()}, Symbol: {symbol.getValue()}, Side: {side.getValue()}, Shares: {ioiShares.getValue()}")

        else:
            # Handle other message types if needed
            print(f"Received message: {message.toString()}")



def main():
    try:
        # Define session settings
        settings = fix.SessionSettings("/home/katana/Kanairo/Python&ML/fix_client.cfg")

        # Create an instance of your application
        application = MyApplication()

        # Create a message store and log factory
        storeFactory = fix.FileStoreFactory(settings)
        logFactory = fix.FileLogFactory(settings)

        # Create an initiator
        initiator = fix.SocketInitiator(application, storeFactory, settings, logFactory)

        # Start the initiator
        initiator.start()

        # Block until the user presses Enter
        input("Press Enter to quit...\n")

        # Stop the initiator
        initiator.stop()

    except (fix.ConfigError, fix.RuntimeError) as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    main()
