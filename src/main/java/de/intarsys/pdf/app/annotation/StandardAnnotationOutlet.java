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
package de.intarsys.pdf.app.annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.intarsys.pdf.cos.COSName;
import de.intarsys.tools.provider.ProviderTools;

/**
 * A VM singleton implementation for {@link IAnnotationOutlet}.
 * <p>
 * This implementation provides for a service provider style registration of
 * {@link IAnnotationFactory} instances by default.
 */
public class StandardAnnotationOutlet implements IAnnotationOutlet {

	private Map<COSName, IAnnotationFactory> instances = new HashMap<COSName, IAnnotationFactory>();

	private boolean initialized = false;

	private boolean lookupProviders = true;

	protected StandardAnnotationOutlet() {
		super();
	}

	protected IAnnotationFactory[] findProviders() {
		List<IAnnotationFactory> result = new ArrayList<IAnnotationFactory>();
		Iterator<IAnnotationFactory> ps = ProviderTools
				.providers(IAnnotationFactory.class);
		while (ps.hasNext()) {
			try {
				result.add(ps.next());
			} catch (Throwable e) {
				// ignore and try on
			}
		}
		return result.toArray(new IAnnotationFactory[result.size()]);
	}

	synchronized public IAnnotationFactory[] getAnnotationFactories() {
		init();
		return instances.values().toArray(
				new IAnnotationFactory[instances.size()]);
	}

	protected void init() {
		if (!lookupProviders || initialized) {
			return;
		}
		initialized = true;
		IAnnotationFactory[] providers = findProviders();
		for (int i = 0; i < providers.length; i++) {
			IAnnotationFactory provider = providers[i];
			registerAnnotationFactory(provider);
		}
	}

	public boolean isLookupProviders() {
		return lookupProviders;
	}

	synchronized public IAnnotationFactory lookupAnnotationFactory(COSName type) {
		init();
		return instances.get(type);
	}

	synchronized public void registerAnnotationFactory(
			IAnnotationFactory factory) {
		instances.put(factory.getAnnotationType(), factory);
	}

	public void setLookupProviders(boolean lookupProviders) {
		this.lookupProviders = lookupProviders;
	}

	synchronized public void unregisterAnnotationFactory(
			IAnnotationFactory factory) {
		instances.remove(factory.getAnnotationType());
	}
}
