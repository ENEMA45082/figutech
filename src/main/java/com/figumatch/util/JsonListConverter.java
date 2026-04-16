package com.figumatch.util;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class JsonListConverter implements AttributeConverter<List<Integer>, String> {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final TypeReference<List<Integer>> TYPE = new TypeReference<>() {};

  @Override
  public String convertToDatabaseColumn(List<Integer> attribute) {
    if (attribute == null) return "[]";
    try {
      return MAPPER.writeValueAsString(attribute);
    }
    catch (Exception e) {
      throw new IllegalStateException("Failed to serialize list", e);
    }
  }

  @Override
  public List<Integer> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.isBlank()) return new ArrayList<>();
    try {
      return MAPPER.readValue(dbData, TYPE);
    }
    catch (Exception e) {
      throw new IllegalStateException("Failed to deserialize list", e);
    }
  }
}
