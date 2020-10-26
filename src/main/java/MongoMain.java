import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class MongoMain {

    static MongoCollection<Document> collection = getCollection("School","student");
    public static void main(String[] args) {
//      insert();//插入数据。执行插入时，可将其他三句函数调用语句注释，下同
//        find(); //查找数据
//        insert();
//        find();
        findOne();
//      update();//更新数据
//      delete();//删除数据
    }

    public static void delete(){
        try{
            //数据库名:School 集合名:student
            //删除符合条件的第一个文档
            collection.deleteOne(Filters.eq("name", "scofield"));
            System.out.println("delete successfully!");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    //MongoDB无需预定义数据库和集合,在使用的时候会自动创建
    public static MongoCollection<Document> getCollection(String dbname,String collectionname){
        //实例化一个mongo客户端,服务器地址：localhost(本地)，端口号：27017
        MongoClient  mongoClient=new MongoClient("192.168.10.133",27017);
        //实例化一个mongo数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase(dbname);
        //获取数据库中某个集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionname);
        return collection;
    }

    public static void insert(){
        try{
            //实例化一个文档,文档内容为{sname:'Mary',sage:25}，如果还有其他字段，可以继续追加append
            Document doc1=new Document("name","scofield").append("score", new Document("english",45).append("math", 100).append("computer",89));
            collection.insertOne(doc1);
            System.out.println("insert successfully!");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public static void find(){
        try{

            MongoCursor<Document>  cursor= collection.find().iterator();
            while(cursor.hasNext()){
                System.out.println(cursor.next().toJson());
            }
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public static void findOne(){
        try{
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("name", "scofield");
            if(collection.find(basicDBObject).first() != null)
                System.out.println(collection.find(basicDBObject).first().toJson());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
