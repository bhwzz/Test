package HelloApp;


/**
* HelloApp/HelloPOATie.java .
* �� IDL-to-Java ������������ֲ�����汾 "3.2" ����
* ���� Hello.idl
* 2006��12��10�� ������ ����01ʱ40��50�� CST
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
