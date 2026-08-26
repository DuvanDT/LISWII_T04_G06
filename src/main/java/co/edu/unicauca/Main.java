IUserRepository repository = new SqliteUserRepository();
UserService userService = new UserService(repository);