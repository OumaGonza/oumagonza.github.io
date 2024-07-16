# -*- coding: utf-8 -*-
import ssl
from socket import socket
import quickfix
import quickfix42 as fix42
import time

class MyApplication(quickfix.Application):

    def onCreate(self, sessionID):
        print(f"onCreate: {sessionID}")
        return

    def onLogon(self, sessionID):
        print(f"onLogon: {sessionID}")
        print("Session %s successfully logged in" % sessionID)
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

# QuickFIX 部分
settings = quickfix.SessionSettings("./configpy.cfg")
application = MyApplication()
storeFactory = quickfix.FileStoreFactory(settings)
logFactory = quickfix.FileLogFactory(settings)

initiator = quickfix.SocketInitiator(application, storeFactory, settings, logFactory)


initiator.start()

# 等待连接建立（可以根据实际情况调整等待时间）
initiator.block()

# 在这里进行 FIX 协议的消息交换
# ...
time.sleep(80)
# 断开 QuickFIX 连接
#initiator.stop()
