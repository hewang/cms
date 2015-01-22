package com.qatang.cms.entity.resource;

import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.enums.ResourcesType;
import com.qatang.cms.enums.YesNoStatus;
import com.qatang.cms.enums.converter.EnableDisableStatusConverter;
import com.qatang.cms.enums.converter.ResourcesTypeConverter;
import com.qatang.cms.enums.converter.YesNoStatusConverter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by likunpeng on 2014/6/25.
 */
@Entity
@Table(name = "c_resource")
@DynamicInsert
@DynamicUpdate
public class Resource implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String url;
	@Column(name = "priority")
	private Integer priority;

    @Convert(converter = EnableDisableStatusConverter.class)
	private EnableDisableStatus valid;
	private String memo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", updatable = false)
	private Date createdTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_time")
	private Date updatedTime;

    @Column(name = "parent_id")
    private Long parentID;

    @Column(name = "type")
    @Convert(converter = ResourcesTypeConverter.class)
    private ResourcesType type;

    @Column(name = "tree_level")
    private Integer treeLevel;
    private String identifier;
    @Column(name = "has_children")

	@Convert(converter = YesNoStatusConverter.class)
    private YesNoStatus hasChildren;

    @Transient
    private List<Resource> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public EnableDisableStatus getValid() {
        return valid;
    }

    public void setValid(EnableDisableStatus valid) {
        this.valid = valid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    public ResourcesType getType() {
        return type;
    }

    public void setType(ResourcesType type) {
        this.type = type;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public YesNoStatus getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(YesNoStatus hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }
}