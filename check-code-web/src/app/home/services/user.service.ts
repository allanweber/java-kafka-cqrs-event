import { UserModel } from './../model/user.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private userSearch = `${environment.queryApi}/github/users`;

  constructor(private httpClient: HttpClient) {}

  searchUser(user: string) {
    const params = new HttpParams().set('query', user);
    return this.httpClient.get<UserModel[]>(this.userSearch, { params });
  }
}
