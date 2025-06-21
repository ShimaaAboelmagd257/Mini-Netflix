package com.example.miniNetflix.mappers;

import com.example.miniNetflix.domain.dto.MovieDto;
import com.example.miniNetflix.domain.entity.MovieEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieMapperImpl implements Mapper<MovieEntity,MovieDto>{

   private ModelMapper mapper;

    public MovieMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public MovieDto mapTo(MovieEntity movieEntity) {
        return mapper.map(movieEntity,MovieDto.class);
    }

    @Override
    public MovieEntity mapFrom(MovieDto movieDto) {
        return mapper.map(movieDto,MovieEntity.class);
    }


}
