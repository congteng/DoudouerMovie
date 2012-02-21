package net.doudouer.movie.recommender;

import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 为用户推荐 用户偏好的 电影
 * 
 * @author congteng
 */
@Service("simpleRecommender")
@Scope("prototype")
public class SimpleMovieRecommender implements Recommender {

	private Recommender recommender;

	private DataModel dataModel;
	
	@Autowired
	public SimpleMovieRecommender(DataModel dataModel) throws TasteException {
		dataModel.refresh(null);
		this.dataModel = dataModel;
		/*double similarity = itemSimilarity.itemSimilarity(5L, 4L);
		System.out.println("4v5 : " + similarity);*/
		
	}

	public List<RecommendedItem> recommend(long userID, int howMany) throws TasteException {
		
		dataModel.refresh(null);
		
		UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
		
		//ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
		
		
		
		UserNeighborhood unh = new NearestNUserNeighborhood(2, userSimilarity, dataModel);
		
		recommender = new GenericUserBasedRecommender(dataModel, unh, userSimilarity);
		
		//recommender = new GenericItemBasedRecommender(dataModel, userSimilarity);
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
