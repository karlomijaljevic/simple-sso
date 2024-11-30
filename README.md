# Simple Single Sing On ~ Simple SSO

**This project is a work in progress and is not used anywhere in production.**

This project is an extremely simple single sign on for your applications and
users without an overly complicated RBAC. It is made with Java and Quarkus on
the backend while serving Qute templates containing basic HTML, CSS and JS on
the frontend. The application holds user data in a simple H2 database together
with app and role data.

There are two predefined roles *user* and *admin*. There will be no layers in
between. The *admin* role manages all user, role and application data. The
*user* if he has no additional role can only view his profile and possible
applications **without** being able to enter them.

Technology stack:
1. Java
2. Quarkus
3. HTML
4. CSS
5. Javascript
6. SQL

Security stack:
1. JWT --> User authorization
2. mTLS --> Application authorization

## How RBAC works in the simple SSO

An application must be first verified by the simple SSO (from further on SSSO)
using mTLS. The application adds the SSSO client certificate to its truststore
and the SSSO adds the application client certificate to its truststore.

Once they have exchanged their public certificates with each other they can
start communication. The application introduces itself by contacting the
*/auth/apply* endpoint. If the SSSO finds the visitors certificate in its
truststore it will respond with a unique ID which the application can use to
register its roles and verify incoming users.

The roles are *"managed"* by the SSSO in such that they can only be removed and
assigned to a user, while the applications registers their available roles via
the */auth/register-roles* endpoint and/or removes existing ones.

The applications internally manage how and where the role can enter. Therefore
once the user comes to the application the application can either contact the
SSSO to see if the user is valid (via the */auth/verify* endpoint) or it can
manually check the JWT parsing it with the publicly available public key of the
SSSO. Whichever the application prefers.

To keep the user authorized the applications should periodically contact the
*/auth/verify* endpoint which will either refresh the users JWT or terminate
the user and send a response which will indicate that the user is timed out.
