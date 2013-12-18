package com.apimock.utils.runtime;

public class TestClass {
	private static final String CLASS = "Test";
	private static final String METHOD = "execute";
	private static final String EXPRESSION = "Math.cos(Math.PI/6)";
	private static final String CONTENT = "public class " + CLASS + " {" + "    public static Object " + METHOD + "() {" + "        return " + EXPRESSION + ";" + "    }" + "}";

	public static void main(String[] args) throws Exception {

		MemoryClassLoader mcl = new MemoryClassLoader(CLASS, CONTENT);
		System.out.println(mcl.loadClass(CLASS).getMethod(METHOD).invoke(null));
	}
}
