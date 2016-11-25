package com.fong2.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


//@Table(name = "user", catalog = "spring_abc", uniqueConstraints = {@UniqueConstraint(columnNames = "email"),
//        @UniqueConstraint(columnNames = "username")})
public class User implements java.io.Serializable {
    public static final String ROLE_ADMIIN = "admin";
    public static final String ROLE_MENBER = "menber";
    private static final long serialVersionUID = 7376961072110620611L;
    private Integer id;
    private String avatar;
    private Long commentCount;

//    @Size(max = 60, message = "公司不能超过六十个字符")
    private String company;

    private Date createAt;

//    @Size(max = 200, message = "描述不能超过两百个字符")
    private String description;

    //@Email(message = "输入正确的邮箱格式")
    private String email;

    //@Size(max = 60, message = "代码库不能超过六十个字符")
    private String github;

    //@Size(max = 60, message = "主页不能超过六十个字符")
    private String homePage;

    //@Size(max = 60, message = "位置不能超过六十个字符")
    private String location;

    //@Pattern(regexp = "^(?!_)(?!.*?_$)[\u4e00-\u9fa5|A-Za-z0-9|_]{3,9}", message = "昵称三到九个字母或者下滑线,下划线不能再开头或结尾")
    private String nick;

    private long number;

    private String password;

    private long points;

    private String role;

    //@Size(max = 60, message = "不能超过六十个字符")
    private String signature;

    private Long topicCount;

    //@Size(max = 60, message = "不能超过六十个字符")
    private String twitter;

    private Date updateAt;

    //@Pattern(regexp = "^(?!_)(?!.*?_$)[A-Za-z0-9|_]{3,9}", message = "用户名三到九个字母或者下滑线,下划线不能再开头或结尾")
    private String username;

    private Set<Follow> followsForFollowerId = new HashSet<Follow>(0);
    private Set<Notification> notifications = new HashSet<Notification>(0);
    private Set<Topic> topics = new HashSet<Topic>(0);
    private Set<Comment> comments = new HashSet<Comment>(0);
    private Set<Focus> focuses = new HashSet<Focus>(0);
    private Set<Collection> collections = new HashSet<Collection>(0);
    private Set<Follow> followsForFollowingId = new HashSet<Follow>(0);
    private Set<Comment> likeComments = new HashSet<Comment>();

    public User() {
    }

    public User(long number, String password, long points, String username) {
        this.number = number;
        this.password = password;
        this.points = points;
        this.username = username;
    }

    public User(String avatar, Long commentCount, String company, Date createAt, String description, String email,
                String github, String homePage, String location, String nick, long number, String password, long points,
                String role, String signature, Long topicCount, String twitter, Date updateAt, String username,
                Set<Follow> followsForFollowerId, Set<Notification> notifications, Set<Topic> topics, Set<Comment> comments,
                Set<Focus> focuses, Set<Collection> collections, Set<Follow> followsForFollowingId, Set<Comment> likeComments) {
        this.avatar = avatar;
        this.commentCount = commentCount;
        this.company = company;
        this.createAt = createAt;
        this.description = description;
        this.email = email;
        this.github = github;
        this.homePage = homePage;
        this.location = location;
        this.nick = nick;
        this.number = number;
        this.password = password;
        this.points = points;
        this.role = role;
        this.signature = signature;
        this.topicCount = topicCount;
        this.twitter = twitter;
        this.updateAt = updateAt;
        this.username = username;
        this.followsForFollowerId = followsForFollowerId;
        this.notifications = notifications;
        this.topics = topics;
        this.comments = comments;
        this.focuses = focuses;
        this.likeComments = likeComments;
        this.collections = collections;
        this.followsForFollowingId = followsForFollowingId;
    }

    //@Id
    //@GeneratedValue(strategy = IDENTITY)

    //@Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //@Column(name = "avatar")
    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    //@Column(name = "comment_count")
    public Long getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    //@Column(name = "company")
    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "create_at", length = 19)
    public Date getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    //@Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //@Column(name = "email", unique = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //@Column(name = "github")
    public String getGithub() {
        return this.github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    //@Column(name = "home_page")
    public String getHomePage() {
        return this.homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    //@Column(name = "location")
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //@Column(name = "nick")
    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    //@Column(name = "number", nullable = false)
    public long getNumber() {
        return this.number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    //@Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //@Column(name = "points", nullable = false)
    public long getPoints() {
        return this.points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    //@Column(name = "role")
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //@Column(name = "signature")
    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    //@Column(name = "topic_count")
    public Long getTopicCount() {
        return this.topicCount;
    }

    public void setTopicCount(Long topicCount) {
        this.topicCount = topicCount;
    }

    //@Column(name = "twitter")
    public String getTwitter() {
        return this.twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "update_at", length = 19)
    public Date getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    //@Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByFollowerId")
    public Set<Follow> getFollowsForFollowerId() {
        return this.followsForFollowerId;
    }

    public void setFollowsForFollowerId(Set<Follow> followsForFollowerId) {
        this.followsForFollowerId = followsForFollowerId;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Notification> getNotifications() {
        return this.notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Topic> getTopics() {
        return this.topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Focus> getFocuses() {
        return this.focuses;
    }

    public void setFocuses(Set<Focus> focuses) {
        this.focuses = focuses;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Collection> getCollections() {
        return this.collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByFollowingId")
    public Set<Follow> getFollowsForFollowingId() {
        return this.followsForFollowingId;
    }

    public void setFollowsForFollowingId(Set<Follow> followsForFollowingId) {
        this.followsForFollowingId = followsForFollowingId;
    }

    //@JsonIgnore
    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "likeUsers")
    public Set<Comment> getLikeComments() {
        return likeComments;
    }

    public void setLikeComments(Set<Comment> likeComments) {
        this.likeComments = likeComments;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", avatar=" + avatar + ", commentCount=" + commentCount + ", company=" + company
				+ ", createAt=" + createAt + ", description=" + description + ", email=" + email + ", github=" + github
				+ ", homePage=" + homePage + ", location=" + location + ", nick=" + nick + ", number=" + number
				+ ", password=" + password + ", points=" + points + ", role=" + role + ", signature=" + signature
				+ ", topicCount=" + topicCount + ", twitter=" + twitter + ", updateAt=" + updateAt + ", username="
				+ username + ", followsForFollowerId=" + followsForFollowerId + ", notifications=" + notifications
				+ ", topics=" + topics + ", comments=" + comments + ", focuses=" + focuses + ", collections="
				+ collections + ", followsForFollowingId=" + followsForFollowingId + ", likeComments=" + likeComments
				+ "]";
	}

    
}
