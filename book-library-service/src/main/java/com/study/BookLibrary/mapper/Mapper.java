package com.study.BookLibrary.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;


public final class Mapper {

  private static final ModelMapper modelMapper = new ModelMapper();

  public Mapper() {
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
  }

  public static <D, T> D map(T source, Class<D> outClass) {
    return modelMapper.map(source, outClass);
  }

  public static <D, T> void map(T source, D destination) {
    modelMapper.map(source, destination);
  }

  public static <D, T> List<D> mapToList(List<T> sources, Class<D> outClass) {
    return sources.stream().map(source -> map(source, outClass))
        .collect(Collectors.toList());
  }

}