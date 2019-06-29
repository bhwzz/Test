package HelloApp;


/**
* HelloApp/HelloPOATie.java .
* 由 IDL-to-Java 编译器（可移植），版本 "3.2" 生成
* 来自 Hello.idl
* 2006年12月10日 星期日 上午01时40分50秒 CST
*/

public class HelloPOATie extends HelloPOA
{

  // Constructors

  public HelloPOATie ( HelloApp.HelloOperations delegate ) {
      this._impl = delegate;
  }
  public HelloPOATie ( HelloApp.HelloOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public HelloApp.HelloOperations _delegate() {
      return this._impl;
  }
  public void _delegate (HelloApp.HelloOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
  }
  public String sayHello ()
  {
    return _impl.sayHello();
  } // sayHello

  public void shutdown ()
  {
    _impl.shutdown();
  } // shutdown

  private HelloApp.HelloOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class HelloPOATie
