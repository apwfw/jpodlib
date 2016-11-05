/*
 * Copyright (c) 2007, intarsys consulting GmbH
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * - Neither the name of intarsys nor the names of its contributors may be used
 *   to endorse or promote products derived from this software without specific
 *   prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package de.intarsys.pdf.font;

/**
 * A special map from a character code to a character code.
 * 
 */
public class CMapBFCharStringMap extends CMapCharMap {

	private final char[] chars;

	private final int last;

	public CMapBFCharStringMap(byte[] source, byte[] destination) {
		super(source);
		this.chars = new char[destination.length >> 1];
		int byteIndex = 0;
		int charIndex = 0;
		while (byteIndex < destination.length) {
			this.chars[charIndex++] = (char) ((destination[byteIndex++] << 8) + destination[byteIndex++]);
		}
		this.last = this.chars.length - 1;
	}

	/**
	 * The CID value for the codepoint.
	 * 
	 * @return The CID value for the codepoint.
	 */
	public int getDestination() {
		return chars[last];
	}

	@Override
	public char[] toChars(int codepoint) {
		if (source == codepoint) {
			return chars;
		} else {
			return null;
		}
	}

	@Override
	public int toCID(int codepoint) {
		if (source == codepoint) {
			return chars[last];
		} else {
			return 0;
		}
	}

	@Override
	public int toCodepoint(int cid) {
		if (chars[last] == cid) {
			return source;
		} else {
			return 0;
		}
	}
}
