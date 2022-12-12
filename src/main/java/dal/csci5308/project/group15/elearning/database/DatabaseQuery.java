package dal.csci5308.project.group15.elearning.database;

import org.springframework.beans.factory.annotation.Value;

public final class DatabaseQuery
{
    @Value("${STORED_PROCEDURE_FORUM_INSERT_NEW_COMMENT}")
    public static String STORED_PROCEDURE_FORUM_INSERT_NEW_COMMENT;
}
