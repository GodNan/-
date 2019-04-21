package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.page.Filter;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.repository.BaseRepository;
import com.css.bdpfnew.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: GodNan
 * @Date: 2018/6/8 15:42
 * @Version: 1.0
 * @Description:
 * 条件+分页+排序
 */
@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, Long> {
    /** baseDao */
    private BaseRepository baseRepository;

    public void setBaseDao(BaseRepository<T, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public Page<T> findPage(PageBean pageBean) {
        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = addRestrictions(root, criteriaQuery, criteriaBuilder, pageBean);
                return predicate;
            }
        };
        return baseRepository.findAll(specification,new PageRequest(pageBean.getPageNumber()-1,pageBean.getPageSize(),addOrders(pageBean)));
    }

    @Override
    public List<T> findAllByPage(PageBean pageBean) {
        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = addRestrictions(root, criteriaQuery, criteriaBuilder, pageBean);
                return predicate;
            }
        };
        return baseRepository.findAll(specification, addOrders(pageBean));
    }

    /**
     * 创建排序
     * @param pageBean
     * @return
     */
    private Sort addOrders(PageBean pageBean) {

        List<Sort.Order> orderList = new ArrayList<Sort.Order>();

        if (StringUtils.isNotEmpty(pageBean.getOrderProperty()) && pageBean.getOrderDirection() != null) {
            if (pageBean.getOrderDirection() == com.css.bdpfnew.model.page.Order.Direction.asc) {
                Sort.Order sort = new Sort.Order(Sort.Direction.ASC, pageBean.getOrderProperty());
                orderList.add(sort);
            } else if (pageBean.getOrderDirection() == com.css.bdpfnew.model.page.Order.Direction.desc) {
                Sort.Order sort = new Sort.Order(Sort.Direction.DESC, pageBean.getOrderProperty());
                orderList.add(sort);
            }
        }
        if (pageBean.getOrders() != null) {
            for (com.css.bdpfnew.model.page.Order order : pageBean.getOrders()) {
                if (order.getDirection() == com.css.bdpfnew.model.page.Order.Direction.asc) {
                    Sort.Order sort = new Sort.Order(Sort.Direction.ASC, order.getProperty());
                    orderList.add(sort);
                } else if (order.getDirection() == com.css.bdpfnew.model.page.Order.Direction.desc) {
                    Sort.Order sort = new Sort.Order(Sort.Direction.DESC, order.getProperty());
                    orderList.add(sort);
                }
            }
        }
        return new Sort(orderList);
    }

    /**
     * 创建查询条件
     * @param root
     * @param criteriaQuery
     * @param criteriaBuilder
     * @param pageBean
     * @return
     */
    private Predicate addRestrictions(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder, PageBean pageBean) {
        if (criteriaQuery == null || pageBean == null) {
            return null;
        }
        if (root == null) {
            return null;
        }
        Predicate restrictions = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();
        if (StringUtils.isNotEmpty(pageBean.getSearchProperty()) && StringUtils.isNotEmpty(pageBean.getSearchValue())) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String> get(pageBean.getSearchProperty()), "%" + pageBean.getSearchValue() + "%"));
        }
        if (pageBean.getFilters() != null) {
            for (Filter filter : pageBean.getFilters()) {
                if (filter == null || StringUtils.isEmpty(filter.getProperty())) {
                    continue;
                }
                if (filter.getOperator() == Filter.Operator.eq && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
//                    if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
//                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(criteriaBuilder.lower(root.<String> get(filter.getProperty())), ((String) filter.getValue()).toLowerCase()));
//                    } else {
//                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
//                    }
                } else if (filter.getOperator() == Filter.Operator.ne && filter.getValue() != null) {
                    if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(criteriaBuilder.lower(root.<String> get(filter.getProperty())), ((String) filter.getValue()).toLowerCase()));
                    } else {
                        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
                    }
                } else if (filter.getOperator() == Filter.Operator.gt && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.gt(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
                } else if (filter.getOperator() == Filter.Operator.lt && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lt(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
                } else if (filter.getOperator() == Filter.Operator.ge && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.ge(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
                } else if (filter.getOperator() == Filter.Operator.le && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.le(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
                } else if (filter.getOperator() == Filter.Operator.like && filter.getValue() != null && filter.getValue() instanceof String) {
                    if(StringUtils.isNotEmpty(filter.getProperty())){
                        // TODO:底层方法需要优化，需要将判断统一整理
                        if((filter.getProperty().indexOf("cityid") > -1 || filter.getProperty().indexOf("areaCode") > -1 || filter.getProperty().indexOf("testid") > -1) && !filter.getProperty().equals("cityidHolder")){
                           if(filter.getProperty().indexOf("testid") > -1){
                               Predicate predicate1 = criteriaBuilder.and(
                                       criteriaBuilder.like(root.<String>get("oldCode"),(String)filter.getValue()+"%")
                               );
                               Predicate predicate2 = criteriaBuilder.and(
                                       criteriaBuilder.like(root.<String>get("newCode"),(String)filter.getValue()+"%")
                               );
                               Predicate predicate = criteriaBuilder.or(predicate1, predicate2);
                               restrictions = criteriaBuilder.and(restrictions, predicate);
                           }else if(((String)filter.getValue()).length() == 2){
                                Predicate predicate1 = criteriaBuilder.and(
                                        criteriaBuilder.greaterThanOrEqualTo(root.<String>get(filter.getProperty()), "110000000000"),
                                        criteriaBuilder.lessThan(root.<String>get(filter.getProperty()), "120000000000"));
                                Predicate predicate2 = criteriaBuilder.and(
                                        criteriaBuilder.greaterThanOrEqualTo(root.<String>get(filter.getProperty()), "110000000"),
                                        criteriaBuilder.lessThan(root.<String>get(filter.getProperty()), "120000000"));
                                Predicate predicate3 = criteriaBuilder.and(
                                        criteriaBuilder.greaterThanOrEqualTo(root.<String>get(filter.getProperty()), "110000"),
                                        criteriaBuilder.lessThan(root.<String>get(filter.getProperty()), "120000"));
                                Predicate predicate = criteriaBuilder.or(predicate1, predicate2, predicate3);
                                restrictions = criteriaBuilder.and(restrictions, predicate);
                            }else if(((String)filter.getValue()).length() == 6){
                                String value = (String) filter.getValue();
                                Long ge12 = Long.valueOf(value) * 1000000L;
                                Long lt12 = Long.valueOf(value) * 1000000L + 1000000L;
                                Predicate predicate1 = criteriaBuilder.and(
                                        criteriaBuilder.greaterThanOrEqualTo(root.<String>get(filter.getProperty()), ge12.toString()),
                                        criteriaBuilder.lessThan(root.<String>get(filter.getProperty()), lt12.toString()));
                                Long ge9 = Long.valueOf(value) * 1000L;
                                Long lt9 = Long.valueOf(value) * 1000L + 1000L;
                                Predicate predicate2 = criteriaBuilder.and(
                                        criteriaBuilder.greaterThanOrEqualTo(root.<String>get(filter.getProperty()), ge9.toString()),
                                        criteriaBuilder.lessThan(root.<String>get(filter.getProperty()), lt9.toString()));
                                Predicate predicate = criteriaBuilder.or(predicate1, predicate2,criteriaBuilder.equal(root.<String>get(filter.getProperty()), value));
                                restrictions = criteriaBuilder.and(restrictions, predicate);
                            }else if(((String)filter.getValue()).length() == 9){
                                String value = (String) filter.getValue();
                                Long ge12 = Long.valueOf(value) * 1000L;
                                Long lt12 = Long.valueOf(value) * 1000L + 1000L;
                                Predicate predicate = criteriaBuilder.and(
                                        criteriaBuilder.greaterThanOrEqualTo(root.<String>get(filter.getProperty()), ge12.toString()),
                                        criteriaBuilder.lessThan(root.<String>get(filter.getProperty()), lt12.toString()));
                                predicate = criteriaBuilder.or(predicate,criteriaBuilder.equal(root.<String>get(filter.getProperty()), value));
                                restrictions = criteriaBuilder.and(restrictions, predicate);
                            }else {
                                restrictions = criteriaBuilder.and(restrictions,
                                        criteriaBuilder.equal(root.<String>get(filter.getProperty()), (String) filter.getValue()));
                            }
                        }else{
                            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String> get(filter.getProperty()), "%" + (String) filter.getValue() + "%"));
                        }
                    }
                } else if (filter.getOperator() == Filter.Operator.in && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).in(filter.getValue()));
                } else if (filter.getOperator() == Filter.Operator.isNull) {
                    restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).isNull());
                } else if (filter.getOperator() == Filter.Operator.isNotNull) {
                    restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).isNotNull());
                } else if (filter.getOperator() == Filter.Operator.gtDate && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThan(root.<Date> get(filter.getProperty()), (Date) filter.getValue()));
                } else if (filter.getOperator() == Filter.Operator.ltDate && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThan(root.<Date> get(filter.getProperty()), (Date) filter.getValue()));
                } else if (filter.getOperator() == Filter.Operator.geDate && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date> get(filter.getProperty()), (Date) filter.getValue()));
                } else if (filter.getOperator() == Filter.Operator.leDate && filter.getValue() != null) {
                    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date> get(filter.getProperty()), (Date) filter.getValue()));
                }
            }
        }
//        criteriaQuery.where(restrictions);
        return restrictions;
    }
}
