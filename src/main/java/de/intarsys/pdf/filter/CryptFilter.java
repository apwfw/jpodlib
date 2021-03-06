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
package de.intarsys.pdf.filter;

import java.io.IOException;

import de.intarsys.pdf.cos.COSDictionary;

/**
 * 
 */
public class CryptFilter extends Filter {
	/**
	 * 
	 */
	public CryptFilter(COSDictionary options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.filter.IFilter#decode(byte[])
	 */
	@Override
	protected byte[] decode(byte[] source) throws IOException {
		// decryption is done in the parser
		// this might produce problems when encryption is not the first
		// filter to apply!
		return source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.intarsys.pdf.filter.IFilter#encode(byte[])
	 */
	@Override
	protected byte[] encode(byte[] source) throws IOException {
		// encryption is done in the writer
		// this might produce problems when encryption is not the last
		// filter to apply!
		return source;
	}
}
