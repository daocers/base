package co.bugu.domain;

import co.bugu.annotation.Column;
import co.bugu.annotation.Id;
import co.bugu.annotation.Index;
import co.bugu.annotation.Table;

/**
 * Created by daocers on 2016/9/27.
 */
@Table(value = "partner")
public class Partner {
    @Id
    @Index
    private Integer id;

    @Column
    private String name;

    @Column
    private String no;
}
