/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.rdf.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * W3C RDF 1.1 serialization syntax.
 * <p>
 * This defines the W3C standardized RDF 1.1 syntaxes like {@link #TURTLE} and
 * {@link #JSONLD}. Note the existence of other RDF syntaxes that are not
 * included here, e.g. <a href="http://www.w3.org/TeamSubmission/n3/">N3</a> and
 * <a href="https://en.wikipedia.org/wiki/TriX_%28syntax%29">TriX</a>.
 * <p>
 * This class is package-protected, its static constants are exposed through
 * {@link RDFSyntax}.
 * 
 * @see RDFSyntax#w3cSyntaxes()
 * @see <a href="https://www.w3.org/TR/rdf11-primer/#section-graph-syntax">RDF
 *      1.1 Primer</a>
 * @see org.apache.commons.rdf.experimental.RDFParser
 */
final class W3CRDFSyntax implements RDFSyntax {

    static final RDFSyntax JSONLD, TURTLE, NQUADS, NTRIPLES, RDFA_HTML, RDFA_XHTML, RDFXML, TRIG;
    static final Set<RDFSyntax> syntaxes;
    
    static {
        // Initialize within static block to avoid inserting nulls
        JSONLD = new W3CRDFSyntax("JSONLD", "JSON-LD 1.0", "application/ld+json", ".jsonld", true);
        TURTLE = new W3CRDFSyntax("TURTLE", "RDF 1.1 Turtle", "text/turtle", ".ttl", false);
        NQUADS = new W3CRDFSyntax("NQUADS", "RDF 1.1 N-Quads", "application/n-quads", ".nq", true);
        NTRIPLES = new W3CRDFSyntax("NTRIPLES", "RDF 1.1 N-Triples", "application/n-triples", ".nt", false);
        RDFA_HTML = new W3CRDFSyntax("RDFA_HTML", "HTML+RDFa 1.1", "text/html", ".html", false);
        RDFA_XHTML = new W3CRDFSyntax("RDFA_XHTML", "XHTML+RDFa 1.1", "application/xhtml+xml", ".xhtml", false);
        RDFXML = new W3CRDFSyntax("RDFXML", "RDF 1.1 XML Syntax", "application/rdf+xml", ".rdf", false);
        TRIG = new W3CRDFSyntax("TRIG", "RDF 1.1 TriG", "application/trig", ".trig", true);

        syntaxes = Collections.unmodifiableSet(new LinkedHashSet<>(
                Arrays.asList(JSONLD, NQUADS, NTRIPLES, RDFA_HTML, RDFA_XHTML, RDFXML, TRIG, TURTLE)));
    }
    
    private final String title;

    private final String mediaType;

    private final String fileExtension;

    private final boolean supportsDataset;

    private final String name;

    private W3CRDFSyntax(String name, String title, String mediaType, String fileExtension, boolean supportsDataset) {
        this.name = name;
        this.title = title;
        this.mediaType = mediaType.toLowerCase(Locale.ROOT);
        this.fileExtension = fileExtension.toLowerCase(Locale.ROOT);
        this.supportsDataset = supportsDataset;
    }

    /**
     * {@inheritDoc}
     * <p>
     * {@link W3CRDFSyntax} always defines media type in lower case, so 
     * {@link String#toLowerCase(Locale)} need not be called.
     * 
     */
    @Override
    public String mediaType() {
        return mediaType;
    }

    /**
     * {@inheritDoc}
     * <p>
     * {@link W3CRDFSyntax} always defines file extensions in lower case, so
     * {@link String#toLowerCase(Locale)} need not be called.
     * 
     */
    @Override
    public String fileExtension() {
        return fileExtension;
    }

    @Override
    public boolean supportsDataset() {
        return supportsDataset;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RDFSyntax)) {
            return false;
        }
        RDFSyntax other = (RDFSyntax) obj;
        return mediaType.equals(other.mediaType().toLowerCase(Locale.ROOT));
    }

    @Override
    public int hashCode() {
        return mediaType.hashCode();
    }

    @Override
    public String toString() {
        return title;
    }

}