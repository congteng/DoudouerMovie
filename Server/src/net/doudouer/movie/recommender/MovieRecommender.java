package net.doudouer.movie.recommender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.doudouer.domain.MovieSimilarity;
import net.doudouer.service.MovieSimilarityService;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.GenericItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 为用户推荐 用户偏好的 电影
 * 
 * @author congteng
 */
@Service("movieRecommender")
@Scope("prototype")
public class MovieRecommender implements Recommender {

	private final Recommender recommender;

	@Autowired
	public MovieRecommender(DataModel dataModel, MovieSimilarityService movieSimilarityService) throws TasteException {
		String hql = "from MovieSimilarity";
		Collection<GenericItemSimilarity.ItemItemSimilarity> correlations = constructMovieSimilarityFromList(movieSimilarityService
				.findEntityByHQL(hql));
		ItemSimilarity itemSimilarity = new GenericItemSimilarity(correlations);
		recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
		recommender.refresh(null);
	}

	/**
	 * 通过service查出MovieSimilarity 构造GenericItemSimilarity.ItemItemSimilarity
	 * @param similarities
	 * @return
	 */
	private List<GenericItemSimilarity.ItemItemSimilarity> constructMovieSimilarityFromList(List<MovieSimilarity> similarities) {
		List<GenericItemSimilarity.ItemItemSimilarity> similarities_ct = new ArrayList<GenericItemSimilarity.ItemItemSimilarity>();

		for (MovieSimilarity simlarity : similarities) {
			GenericItemSimilarity.ItemItemSimilarity itemSimilarity = new GenericItemSimilarity.ItemItemSimilarity(simlarity.getMovieID1(), simlarity
					.getMovieID2(), simlarity.getSimilarity());

			similarities_ct.add(itemSimilarity);
		}
		return similarities_ct;
	}

	public List<RecommendedItem> recommend(long userID, int howMany) throws TasteException {
		return recommender.recommend(userID, howMany);
	}

	public float estimatePreference(long userID, long itemID) throws TasteException {
		return recommender.estimatePreference(userID, itemID);
	}

	public void setPreference(long userID, long itemID, float value) throws TasteException {
		recommender.setPreference(userID, itemID, value);
	}

	public void removePreference(long userID, long itemID) throws TasteException {
		recommender.removePreference(userID, itemID);
	}

	public DataModel getDataModel() {
		return recommender.getDataModel();
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		recommender.refresh(alreadyRefreshed);
	}

	public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer) throws TasteException {
		return recommend(userID, howMany, rescorer);
	}

	public String toString() {
		return "MovieRecommender[recommender:" + recommender + ']';
	}
}
