package net.doudouer.movie.recommender;

import javax.annotation.Resource;

import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;

/**
 * 为用户推荐 相近爱好的用户
 * 
 * @author congteng
 */
@Service("userRecommender")
public class UserRecommender {
	
	@Resource(name = "movieDataModel")
	private DataModel dataModel;
	
	public long[] getSimilarUserIdList(Long userId, int howmany) {
		try {
			dataModel.refresh(null);
			UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(howmany, userSimilarity, dataModel);
			long[] ids = neighborhood.getUserNeighborhood(userId);
			return ids;
		} catch (Exception e) {
			return null;
		}
	}

}
