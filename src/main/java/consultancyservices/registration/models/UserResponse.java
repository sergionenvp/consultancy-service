package consultancyservices.registration.models;

public class UserResponse extends UserRequest {



    private Long Id;

    public UserResponse() {
        this(null, null, null, 0);
    }

    public UserResponse(Long id, String fullName, String locality, int age) {
        super(fullName, locality, age);
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
