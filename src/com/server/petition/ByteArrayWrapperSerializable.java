package com.server.petition;

import java.io.Serializable;

public class ByteArrayWrapperSerializable implements Serializable{
	private byte[] array;

	public ByteArrayWrapperSerializable(byte[] array) {
		this.array = array;
	}

	public byte[] getArray() {
		return array;
	}
}
