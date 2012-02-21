package net.doudouer.service.impl;

import javax.annotation.Resource;

import net.doudouer.dao.BaseDao;
import net.doudouer.domain.MovieSimilarity;
import net.doudouer.service.MovieSimilarityService;

import org.springframework.stereotype.Service;

@Service("movieSimilarityService")
public class MovieSimilarityServiceImpl extends BaseServiceImpl<MovieSimilarity> implements MovieSimilarityService {
	
	@Resource(name = "movieSimilarityDao")
	public void setDao(BaseDao<MovieSimilarity> dao) {
		super.setDao(dao);
	}
	
}
