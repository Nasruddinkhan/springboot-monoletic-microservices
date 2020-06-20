package com.mypractice.microservices.userapi.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
/**
 * nasru - Jun 20, 2020
 * ObjectUtilMapper.java 
 */
public class ObjectUtilMapper {
	private static ModelMapper modelMapper = new ModelMapper();
	static {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	private ObjectUtilMapper() {
		super();
	}
	public static<S, D> D map(final S source, Class<D> destination) {
		return modelMapper.map(source, destination);
	}
	
	public static<S, D> List<D> mapAll(final List<S> source, Class<D> destination) {
		return source.stream().map(m-> map(m, destination)).collect(Collectors.toList());
	}
	
}
