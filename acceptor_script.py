#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import quickfix as fix


class MyApplication(fix.Application):
    def onCreate(self, sessionID):
        print(f"onCreate: {sessionID}")
        return

    def onLogon(self, sessionID):
        print(f"onLogon: {sessionID}")

        # Initiating the sending of a NewOrderSingle message after logon
        self.sendNewOrderSingle(sessionID)

        return

    def onLogout(self, sessionID):
        print(f"onLogout: {sessionID}")

    # Print the session status
        print(f"Session Status: {fix.Session.lookupSession(sessionID).getSessionState()}")
        return


    def toAdmin(self, message, sessionID):
        print(f"toAdmin: {sessionID}")
        return

    def fromAdmin(self, message, sessionID):
        print(f"fromAdmin: {sessionID}")
        return

    def toApp(self, message, sessionID):
        print(f"toApp: {sessionID}")
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
        # Implement logic to process received messages
        print(f"Received message: {message}")

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
