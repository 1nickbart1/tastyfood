package by.tastyfood.dao.impl;

import by.tastyfood.dao.AbstractDaoImpl;
import by.tastyfood.dao.entity.Comments;
import by.tastyfood.dao.i.ICommentsDao;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay on 22.06.2017.
 */
@Service
public class CommentsDao extends AbstractDaoImpl<Comments,Long> implements ICommentsDao {

    public  CommentsDao(){
        super(Comments.class);
    }
}
