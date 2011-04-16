package com.server.petition;

import java.io.Serializable;

public class ByteArrayWrapper {
	private byte[] array;

	public ByteArrayWrapper(byte[] array) {
		this.array = array;
	}

	public byte[] getArray() {
		return array;
	}
}
