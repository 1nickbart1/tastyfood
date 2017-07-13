package by.tastyfood.model.comments;

import by.tastyfood.dao.entity.Comments;
import by.tastyfood.dao.i.ICommentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 22.06.2017.
 */
@Service
public class CommentsService {
    @Autowired
    ICommentsDao commentsDao;

    public Long saveComment(Comments comments){
       return commentsDao.add(comments);
    }
}
