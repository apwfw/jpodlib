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
 * An abstract implementation for map definitions in a CMap.
 * 
 */
abstract public class CMapMap {

	protected CMapMap() {
		super();
	}

	/**
	 * The string value for a CID or null if not applicable.
	 * 
	 * @param cid
	 * @return The string value for a CID.
	 */
	abstract public char[] toChars(int cid);

	/**
	 * The CID value for the codepoint or 0 if not applicable.
	 * <p>
	 * With /ToUnicode maps, this is used to get a UTF_16BE encoded unicode
	 * value for a codepoint.
	 * 
	 * @param codepoint
	 *            The (encoded) codepoint value.
	 * 
	 * @return The CID value for the codepoint.
	 */
	abstract public int toCID(int codepoint);

	/**
	 * The codepoint for a CID or 0 if not applicable.
	 * 
	 * @param cid
	 * @return The codepoint for a CID.
	 */
	abstract public int toCodepoint(int cid);
}
