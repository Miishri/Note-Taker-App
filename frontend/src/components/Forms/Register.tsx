import React from 'react';
import type { RegisterFormUser } from '../../interfaces/Forms';
import { ValidateEmail } from '../../services/validator.service';
import { HiOutlineEye, HiOutlineEyeOff } from 'react-icons/hi';
// eslint-disable-next-line @typescript-eslint/no-empty-interface
interface RegisterFormProps {}

interface RegisterFormState {
  user: RegisterFormUser;
  errors: Record<string, string>;
  showPassword: Record<string, boolean>;
}

export class RegisterForm extends React.Component<RegisterFormProps, RegisterFormState> {
  constructor(props: RegisterFormProps) {
    super(props);

    this.state = {
      user: {
        username: '',
        email: '',
        password: '',
        password_confirmation: ''
      },
      errors: {},
      showPassword: {
        password: false,
        password_confirmation: false
      }
    };
  }

  handleInputChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = event.target;
    this.setState((prevState) => ({
      user: { ...prevState.user, [name]: value }
    }));
  };

  togglePasswordVisibility = (name: string): void => {
    this.setState((prevState) => ({
      showPassword: { ...prevState.showPassword, [name]: !prevState.showPassword[name] }
    }));
  };

  handleSubmit = (event: React.FormEvent<HTMLFormElement>): void => {
    event.preventDefault();

    const { user } = this.state;
    const errors: Record<string, string> = {};

    // Validate email
    if (!ValidateEmail(user.email)) {
      errors.email = 'Please enter a valid email address';
    }

    // Validate password confirmation
    if (user.password !== user.password_confirmation) {
      errors.password_confirmation = 'Password confirmation does not match password';
    }

    if (Object.keys(errors).length > 0) {
      this.setState({ errors });
    } else {
      // Success
      // TODO: Make Request to Register Endpoint with user object
      console.log(user);
    }
  };

  render(): React.ReactElement<HTMLFormElement> {
    const { user, errors, showPassword } = this.state;

    return (
      <form onSubmit={this.handleSubmit} className="mx-auto flex flex-col justify-center">
        {Object.getOwnPropertyNames(user).map((name, j: number) => {
          const isPasswordVisible = name.startsWith('password') ? showPassword[name] : true;

          return (
            <div className="mb-6" key={j}>
              <label htmlFor={name} className="block text-gray-700 font-bold mb-2">
                {name.charAt(0).toUpperCase() + name.slice(1).replace('_', ' ')}
              </label>
              <div className="relative">
                <input
                  type={isPasswordVisible ? 'text' : 'password'}
                  id={name}
                  name={name}
                  value={user[name]}
                  onChange={this.handleInputChange}
                  required
                  className={`appearance-none border rounded-md w-full py-3 px-4 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${
                    Boolean(errors[name]) && errors[name].length > 0 ? 'border-red-500' : ''
                  }`}
                />
                <button
                  type="button"
                  onClick={() => {
                    this.togglePasswordVisibility(name);
                  }}
                  className="absolute right-0 top-1/2 transform -translate-y-1/2 px-3 py-2">
                  {name.startsWith('password') && (
                    <>
                      {isPasswordVisible ? (
                        <HiOutlineEyeOff className="h-5 w-5 text-gray-400" />
                      ) : (
                        <HiOutlineEye className="h-5 w-5 text-gray-400" />
                      )}
                    </>
                  )}
                </button>
              </div>
              {Boolean(errors[name]) && errors[name].length > 0 && (
                <p className="text-red-500">{errors[name]}</p>
              )}
            </div>
          );
        })}
      </form>
    );
  }
}
