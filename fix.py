#!/usr/bin/env python3
# -*- coding: utf-8 -*-


import quickfix as fix

class MyApplication(fix.Application):
    def onCreate(self, sessionID):
        print(f"onCreate: {sessionID}")
        return

    def onLogon(self, sessionID):
        print(f"onLogon: {sessionID}")
        return

    def onLogout(self, sessionID):
        print(f"onLogout: {sessionID}")
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
        return

def main():
    try:
        # Define session settings
        settings = fix.SessionSettings("path/to/your/fix_client.cfg")

        # Create an instance of your application
        application = MyApplication()

        # Create a message store and log factory
        storeFactory = fix.FileStoreFactory(settings)
        logFactory = fix.FileLogFactory(settings)

        # Create a message factory
        messageFactory = fix.DefaultMessageFactory()

        # Create an initiator
        initiator = fix.SocketInitiator(application, storeFactory, settings, logFactory, messageFactory)

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

