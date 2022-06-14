import { AuthConfig } from "angular-oauth2-oidc";

export const authConfig: AuthConfig = {
  issuer: 'https://accounts.google.com',
  redirectUri: 'http://localhost:4200/oauth',
  clientId: '524872866401-tpkkalp5ucs0pe3t0k3q1i47o7p0tsss.apps.googleusercontent.com',
  scope: 'openid profile email',
  clearHashAfterLogin: false,
  strictDiscoveryDocumentValidation: false
};

export const googleRegisterConfig: AuthConfig = {
  issuer: 'https://accounts.google.com',
  redirectUri: 'http://localhost:4200/google_register',
  clientId: '524872866401-tpkkalp5ucs0pe3t0k3q1i47o7p0tsss.apps.googleusercontent.com',
  scope: 'openid profile email',
  clearHashAfterLogin: false,
  strictDiscoveryDocumentValidation: false
};


export const googleLoginConfig: AuthConfig = {
  issuer: 'https://accounts.google.com',
  redirectUri: 'http://localhost:4200/google_login',
  clientId: '524872866401-tpkkalp5ucs0pe3t0k3q1i47o7p0tsss.apps.googleusercontent.com',
  scope: 'openid profile email',
  clearHashAfterLogin: false,
  strictDiscoveryDocumentValidation: false
};
