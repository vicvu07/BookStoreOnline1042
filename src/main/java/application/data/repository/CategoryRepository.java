package application.data.repository;

import application.data.model.Category;
import application.model.viewmodel.common.ChartLabelDataVM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Query("select count(c.id) from dbo_category c")
    long getTotalCategories();

    @Query("select distinct new application.model.viewmodel.common.ChartLabelDataVM(c.name, COUNT(c.id)) " +
            "from dbo_category c inner join c.listBooks p" +
            " group by c.id"+
            " order by c.name asc")
    List<ChartLabelDataVM> countBookByCategory();

    @Query( "select distinct new application.model.viewmodel.common.ChartLabelDataVM(c.name, SUM(op.amount))" +
            " from dbo_order_book op" +
            " inner join op.book p"+
            " inner join  p.category c" +
            " group by c.id" +
            " order by c.name asc")
    List<ChartLabelDataVM> sumBookByCategory();


}
