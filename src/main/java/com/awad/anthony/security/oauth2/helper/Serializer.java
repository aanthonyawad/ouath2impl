package com.awad.anthony.security.oauth2.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import flexjson.JSONSerializer;

public class Serializer {

public static final OutputStream serialize(Object o) {
		
		
		OutputStream out = new ByteArrayOutputStream();
		JSONSerializer serializer = new JSONSerializer();
		OutputStreamWriter writer = new OutputStreamWriter(out, Charset.forName("UTF-8"));
				//new OutputStreamWriter(out);
		serializer.rootName("result");
		serializer.exclude("*.class");
		serializer.deepSerialize(o, writer);

		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {

		}
		return out;
	}

	
}
