package br.com.tads.polia.poliaappsrv.infrastructure.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Base64;

public class ClobDeserializer extends JsonDeserializer<Clob> {
  @Override
  public Clob deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
    String base64 = p.getValueAsString();
    byte[] bytes = Base64.getDecoder().decode(base64);
    String text = new String(bytes, StandardCharsets.UTF_8);
    try {
        return new SerialClob(text.toCharArray());
    } catch (SerialException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
  }
}