package transport;

import java.io.IOException;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;

public class MySoapEnvelope extends SoapSerializationEnvelope{

    public MySoapEnvelope(int version) {
		super(version);
	}

	/**
     * Writes the complete envelope including header and body elements to the
     * given XML writer.
     */
    public void write(XmlSerializer writer) throws IOException {
        writer.setPrefix("i", xsi);
        writer.setPrefix("d", xsd);
        writer.setPrefix("c", enc);
        writer.setPrefix("soap", env);
        writer.startTag(env, "Envelope");
        writer.startTag(env, "Header");
        writeHeader(writer);
        writer.endTag(env, "Header");
        writer.startTag(env, "Body");
        writeBody(writer);
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }

}
