










## Naming conventions
- Use PascalCase in components, interfaces, or type aliases

- Use camelCase for JavaScript data types like variables, arrays, objects, functions, etc.

- Use camelCase for folder and non-component file names and PascalCase for component file names

# General Conventions
- Avoid default export
- No "Any" typings are allowed












# Component Conventions
Follow this general template


```typescript
    // 1. Imports - Prefer destructuring imports to minimize writen code
import React, { PropsWithChildren, useState, useEffect } from "react";

// 2. Types
type ComponentProps = {
  someProperty: string;
};

// 3. Styles - with @mui use styled API or sx prop of the component
const Wrapper = styled("div")(({ theme }) => ({
  color: theme.palette.white
}));

// 4. Additional variables
const SOME_CONSTANT = "something";

// 5. Component
function Component({ someProperty }: PropsWithChildren<ComponentProps>) {
  // 5.1 Definitions
  const [state, setState] = useState(true);
  const { something } = useSomething();

  // 5.2 Functions
  function handleToggleState() {
    setState(!state);
  }

  // 5.3 Effects
  // ❌
  React.useEffect(() => {
    // ...
  }, []);

  // ✅
  useEffect(() => {
    // ...
  }, []);

  // 5.5 Additional destructures
  const { property } = something;

  return (
    <div>
      {/* Separate elements if not closed on the same line to make the code clearer */}
      {/* ❌ */}
      <div>
        <div>
          <p>Lorem ipsum</p>
          <p>Pellentesque arcu</p>
        </div>
        <p>Lorem ipsum</p>
        <p>Pellentesque arcu</p>
      </div>
      <div>
        <p>
          Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Pellentesque
          arcu. Et harum quidem rerum facilis est et expedita distinctio.
        </p>
        <p>Pellentesque arcu</p>
        <p>
          Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Pellentesque
          arcu. Et harum quidem rerum facilis est et expedita distinctio.
        </p>
      </div>

      {/* ✅ */}
      <Wrapper>
        <div>
          <p>Lorem ipsum</p>
          <p>Pellentesque arcu</p>
        </div>

        <p>Lorem ipsum</p>
        <p>Pellentesque arcu</p>
      </Wrapper>

      <div>
        <div>
          <p>
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
            Pellentesque arcu. Et harum quidem rerum facilis est et expedita
            distinctio.
          </p>

          <p>Pellentesque arcu</p>

          <p>
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
            Pellentesque arcu. Et harum quidem rerum facilis est et expedita
            distinctio.
          </p>
        </div>
      </div>
    </div>
  );
}

// 6. Exports
export { Component };
export type { ComponentProps };
```


### Seperate function from the jsx if it takes more than one line 


```typescript
    // ❌
<button
  onClick={() => {
    setState(!state);
    resetForm();
    reloadData();
  }}
/>

// ✅
<button onClick={() => setState(!state)} />

// ✅
const handleButtonClick = () => {
  setState(!state);
  resetForm();
  reloadData();
}

<button onClick={handleButtonClick} />
```


### Prefer destructuring properties
```typescript
    // ❌
const Button = (props) => {
  return <button>{props.text}</button>;
};

// ✅
const Button = (props) => {
  const { text } = props;

  return <button>{text}</button>;
};

// ✅
const Button = ({ text }) => {
  return <button>{text}</button>;
};
```


### Avoid large components




### Group the state whenever possible 
```typescript
// ❌
const [username, setUsername] = useState('')
const [password, setPassword] = useState('')

// ✅
const [user, setUser] = useState({})
```

### Use shorthand for boolean props

```typescript
// ❌
<Form hasPadding={true} withError={true} />

// ✅
<Form hasPadding withError />
```

### Prefer conditional rendering with ternary operator

```typescript
    const { role } = user;

// ❌
if (role === ADMIN) {
  return <AdminUser />;
} else {
  return <NormalUser />;
}

// ✅
return role === ADMIN ? <AdminUser /> : <NormalUser />;
```

### Use implicit return in small functions

```typescript
    
// ❌
const add = (a, b) => {
  return a + b;
}

// ✅
const add = (a, b) => a + b;
```