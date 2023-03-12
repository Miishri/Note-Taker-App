import { type User } from './User';

export interface RegisterResponse {
  data: {
    user: User;
  };
}
