package transport;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class UserSerializable implements KvmSerializable {
	
	private Long id;
	private String email;
	private String nickName;
	private String password;
	private Long timestamp;
	private String avatarLink;
	private String[] newsTypeArray = new String[]{"addFriend","recordGibberish","writeFilmReview","collectFilm","discussReview"};
	private String newsTypes;
	
	private int propertyCount = 3;
	
	@Override
	public Object getProperty(int arg0) {
		switch (arg0){
        case 0:
            return avatarLink;
        case 1:
            return email;
        case 2:
            return id;
        case 3:
        	return newsTypeArray;
        case 4:
        	return newsTypes;
        case 5:
        	return nickName;
        case 6:
        	return password;
        case 7:
        	return timestamp;
        default:
            return null;
        }
	}

	@Override
	public int getPropertyCount() {
		return propertyCount;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo pi) {
		switch (arg0){
        case 0:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "avatarLink";
            break;
        case 1:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "email";
        case 2:
        	pi.type = PropertyInfo.LONG_CLASS;
            pi.name = "id";
            break;
        case 3:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "newsTypeArray";
            break;
        case 4:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "newsTypes";
            break;
        case 5:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "nickName";
            break;
        case 6:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "password";
            break;
        case 7:
        	pi.type = PropertyInfo.LONG_CLASS;
            pi.name = "timestamp";
            break;
        default:
        	break;
		}
	}

	@Override
	public void setProperty(int arg0, Object object) {
		switch (arg0){
        case 0:
            avatarLink = (String)object;
            break;
        case 1:
        	email = (String)object;
        case 2:
        	id = (Long)object;
            break;
        case 3:
        	newsTypeArray = (String[])object;
            break;
        case 4:
        	newsTypes = (String)object;
            break;
        case 5:
        	nickName = (String)object;
            break;
        case 6:
        	password = (String)object;
            break;
        case 7:
        	timestamp = (Long)object;
            break;
        default:
        	break;
		}
	}

}
