package net.doudouer.struts.action;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import net.doudouer.domain.Movie;
import net.doudouer.service.MovieService;
import net.doudouer.util.ValidateUtil;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 电影相关服务的Action
 * @author congteng
 *
 */
@Controller
@Scope("prototype")
public class MovieAction extends BaseAction<Movie> implements ServletContextAware{

	private static final long serialVersionUID = 4458897062545383636L;

	// 模型
	private Movie model = new Movie();
	
	@Resource(name = "movieService")
	private MovieService movieService;
	
	
	/* 电影海报 */
	private File moviePoster;
	/* 上传文件名 */
	private String moviePosterFileName;
	/* 接收ServletContext */
	private ServletContext servletContext;
	
	/**
	 * 去添加电影页面
	 * @return
	 */
	public String toAddMovie(){
		return "addMoviePage";
	}

	/**
	 * 添加电影
	 * @return
	 */
	public String addMovie(){
		try {
			if(moviePoster != null){
				// 以下是 上传电影图片
				String dir = servletContext.getRealPath("/upload/moviePoster");
				File dirFile = new File(dir);
				if(!dirFile.exists()){
					dirFile.mkdir();
				}
				//取得系统的纳秒时间
				long nano = System.nanoTime();
				//提取文件的扩展名
				String ext = moviePosterFileName.substring(moviePosterFileName.lastIndexOf("."));
				File newFile = new File(dir,nano + ext);
				//通过重命名实现文件上传
				moviePoster.renameTo(newFile);
				// 删除自己
				moviePoster.delete();
				//需要将文件完整路径名入库
				model.setAvatarLink("upload/moviePoster/" + nano + ext);
			}
			movieService.saveEntity(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SUCCESS;
	}

	public void validateAddMovie(){
		if(!ValidateUtil.isValid(model.getMovieName())){
			addFieldError("movieName", "电影名不能为空");
		}
		if(!ValidateUtil.isValid(model.getType())){
			addFieldError("type", "电影类型不能为空");
		}
		if(hasErrors()){
			return ;
		}
	}
	
	////////////////////////////////
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public File getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(File moviePoster) {
		this.moviePoster = moviePoster;
	}

	public String getMoviePosterFileName() {
		return moviePosterFileName;
	}

	public void setMoviePosterFileName(String moviePosterFileName) {
		this.moviePosterFileName = moviePosterFileName;
	}

	@Override
	public Movie getModel() {
		return model;
	}
}
