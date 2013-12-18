package com.apimock.utils.runtime;

import java.util.HashMap;
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject.Kind;

@SuppressWarnings("rawtypes")
class MemoryFileManager extends ForwardingJavaFileManager {
	final Map<String, Output> map = new HashMap<String, Output>();

	@SuppressWarnings("unchecked")
	MemoryFileManager(JavaCompiler compiler) {
		super(compiler.getStandardFileManager(null, null, null));
	}

	@Override
	public Output getJavaFileForOutput(Location location, String name, Kind kind, FileObject source) {
		Output mc = new Output(name, kind);
		this.map.put(name, mc);
		return mc;
	}
}