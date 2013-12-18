package com.apimock.utils.runtime;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

class Source extends SimpleJavaFileObject {
	private final String content;

	Source(String name, Kind kind, String content) {
		super(URI.create("memo:///" + name.replace('.', '/') + kind.extension), kind);
		this.content = content;
	}

	@Override
	public CharSequence getCharContent(boolean ignore) {
		return this.content;
	}
}