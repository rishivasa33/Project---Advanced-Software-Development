package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.forum;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ForumMockDatabase implements IDatabaseOperations
{
    private List<ForumTopic> forumTopicList;

    public ForumMockDatabase()
    {
        forumTopicList = ForumFactory.instance().makeForumTopicList();
    }

    @Override
    public int create(String query, Object... params) throws SQLException
    {
        ForumTopic topic = ForumFactory.instance().makeForumTopic();
        ForumTopicResponse response = ForumFactory.instance().makeForumTopicResponse();

        topic.setId(String.valueOf(forumTopicList.size() + 1));
        topic.setTopic(String.valueOf(params[1]));
        topic.setCourseId(String.valueOf(params[0]));
        topic.setCreatedBy(String.valueOf(params[2]));
        topic.setCreatedOn("Now");

        forumTopicList.add(topic);

        return 1;
    }

    @Override
    public Map<String, List<Object>> read(String query, Object... params) throws SQLException
    {
        Map<String, List<Object>> forumTopicMap = new HashMap<>();

        forumTopicMap.put("TOPIC_ID", new LinkedList<>());
        forumTopicMap.put("TOPIC", new LinkedList<>());
        forumTopicMap.put("TOPIC_CREATED_BY", new LinkedList<>());
        forumTopicMap.put("COURSE_ID", new LinkedList<>());
        forumTopicMap.put("CREATED_ON", new LinkedList<>());

        forumTopicMap.put("REPLY_ID", new LinkedList<>());
        forumTopicMap.put("REPLY", new LinkedList<>());
        forumTopicMap.put("REPLY_BY", new LinkedList<>());
        forumTopicMap.put("REPLY_CREATED_ON", new LinkedList<>());

        for(ForumTopic topic: forumTopicList)
        {
            List<Object> list1 = forumTopicMap.get("TOPIC_ID");
            list1.add(topic.getId());

            List<Object> list2 = forumTopicMap.get("TOPIC");
            list2.add(topic.getTopic());

            List<Object> list3 = forumTopicMap.get("TOPIC_CREATED_BY");
            list3.add(topic.getCreatedBy());

            List<Object> list4 = forumTopicMap.get("COURSE_ID");
            list4.add(topic.getCourseId());

            List<Object> list5 = forumTopicMap.get("CREATED_ON");
            list5.add(topic.getCreatedOn());

            forumTopicMap.put("TOPIC_ID", list1);
            forumTopicMap.put("TOPIC", list2);
            forumTopicMap.put("TOPIC_CREATED_BY", list3);
            forumTopicMap.put("COURSE_ID", list4);
            forumTopicMap.put("CREATED_ON", list5);

            if(topic.getReplyList().size() > 0)
            {
                for(ForumTopicResponse response: topic.getReplyList())
                {
                    List<Object> list6 = forumTopicMap.get("REPLY_ID");
                    list6.add(response.getId());

                    List<Object> list7 = forumTopicMap.get("REPLY");
                    list7.add(response.getReply());

                    List<Object> list8 = forumTopicMap.get("REPLY_BY");
                    list8.add(response.getReply());

                    List<Object> list9 = forumTopicMap.get("REPLY_CREATED_ON");
                    list9.add(response.getReply());

                    forumTopicMap.put("REPLY_ID", list6);
                    forumTopicMap.put("REPLY", list7);
                    forumTopicMap.put("REPLY_BY", list8);
                    forumTopicMap.put("REPLY_CREATED_ON", list9);
                }
            }
            else
            {
                List<Object> list6 = forumTopicMap.get("REPLY_ID");
                list6.add("");

                List<Object> list7 = forumTopicMap.get("REPLY");
                list7.add("");

                List<Object> list8 = forumTopicMap.get("REPLY_BY");
                list8.add("");

                List<Object> list9 = forumTopicMap.get("REPLY_CREATED_ON");
                list9.add("");

                forumTopicMap.put("REPLY_ID", list6);
                forumTopicMap.put("REPLY", list7);
                forumTopicMap.put("REPLY_BY", list8);
                forumTopicMap.put("REPLY_CREATED_ON", list9);
            }
        }

        return forumTopicMap;
    }

    @Override
    public int update(String query, Object... params) throws SQLException
    {
        return create(query, params);
    }

    @Override
    public int delete(String query, Object... params) throws SQLException
    {
        return create(query, params);
    }

    @Override
    public Object getValueAt(Map<String, List<Object>> map, String columnName, int row)
    {
        return map.get(columnName).get(row);
    }

    @Override
    public int getRowCount(Map<String, List<Object>> map)
    {
        Iterator<String> iterator = map.keySet().iterator();
        String columnName = iterator.next();
        return  map.get(columnName).size();
    }
}
