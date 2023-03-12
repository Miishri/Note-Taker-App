export interface RegisterFormUser {
  username: string;
  email: string;
  password: string;
  [key: string]: string;
  password_confirmation: string;
}

export interface LoginFormUser {
  username: string;
  password: string;
  [key: string]: string;
}
