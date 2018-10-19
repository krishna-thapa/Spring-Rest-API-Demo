package com.krishna.api.mode;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @Column(name = "AVAIL_BALANCE")
    private Float availableBalance;
    @Column(name = "CLOSE_DATE")
    private Date closeDate;
    @Column(name = "LAST_ACTIVITY_DATE")
    private Date lastActivityDate;
    @Column(name = "OPEN_DATE")
    private Date openDate;
    @Column(name = "PENDING_BALANCE")
    private Double pendingBalance;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CUST_ID")
    private Integer customerId;
    @Column(name = "OPEN_BRANCH_ID")
    private Integer openBranchId;
    @Column(name = "OPEN_EMP_ID")
    private Integer employeeId;
    @Column(name = "PRODUCT_CD")
    private String productCD;

}
