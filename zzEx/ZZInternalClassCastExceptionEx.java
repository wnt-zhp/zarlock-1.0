package zzEx;

public class ZZInternalClassCastExceptionEx extends ZZInternalEx {
	
	private Class trigger;
	
	public ZZInternalClassCastExceptionEx(Throwable arg0, Class trigger) {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.trigger = trigger;
	}

	public ZZInternalClassCastExceptionEx(String arg0, Throwable arg1, Class trigger) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		this.trigger = trigger;
	}

	public ZZInternalClassCastExceptionEx(String arg0, Class trigger) {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.trigger = trigger;
	}

	public ZZInternalClassCastExceptionEx(Class trigger) {
		super();
		// TODO Auto-generated constructor stub
		this.trigger = trigger;
	}

	public Class getTriggeringClass(){
		return trigger;
	}

	public ZZInternalClassCastExceptionEx() {
		super();
	}

	public ZZInternalClassCastExceptionEx(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ZZInternalClassCastExceptionEx(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ZZInternalClassCastExceptionEx(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
}
