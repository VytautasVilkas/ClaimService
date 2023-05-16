// import React, { useContext } from 'react';
// import { Route, Navigate, Outlet } from 'react-router-dom';
// import { UserContext } from './UserContext';

// const PrivateRoutes = ({ component: Component, ...rest }) => {
//   const { isLoggedIn } = useContext(UserContext);

//   return (
//     <Route
//       {...rest}
//       render={(props) =>
//         isLoggedIn ? (
//           <Component {...props}>
//             <Outlet /> {/* Add the outlet component here */}
//           </Component>
//         ) : (
//           <Navigate to="/" />
//         )
//       }
//     />
//   );
// };

// export default PrivateRoutes;
