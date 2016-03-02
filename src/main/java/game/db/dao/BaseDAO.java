package game.db.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by Windows on 14.01.2016.
 */


public abstract class BaseDAO {

    protected HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }
}
