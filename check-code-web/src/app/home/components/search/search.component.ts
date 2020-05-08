import { UserModel } from './../../model/user.model';
import { UserService } from './../../services/user.service';
import { Subscription, fromEvent } from 'rxjs';
import {
  Component,
  OnInit,
  OnDestroy,
  ViewChild,
  ElementRef,
  AfterViewInit,
} from '@angular/core';
import {
  filter,
  debounceTime,
  distinctUntilChanged,
  tap,
} from 'rxjs/operators';

@Component({
  selector: 'check-code-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.sass'],
})
export class SearchComponent implements OnInit, OnDestroy, AfterViewInit {
  private serviceSubscription: Subscription;
  @ViewChild('input') input: ElementRef;
  public users: UserModel[] = [];
  public loading = false;

  constructor(private userService: UserService) {}

  ngOnInit(): void {}

  ngAfterViewInit() {
    fromEvent(this.input.nativeElement, 'keyup')
      .pipe(
        filter(Boolean),
        debounceTime(500),
        distinctUntilChanged(),
        tap(() => {
          this.search(this.input.nativeElement.value);
        })
      )
      .subscribe();
  }

  search(userName: string) {
    if (userName === '') {
      this.users = [];
      return;
    }
    this.toggleLoading();
    this.serviceSubscription = this.userService.searchUser(userName).subscribe(
      (response) => (this.users = response),
      () => this.toggleLoading(),
      () => this.toggleLoading()
    );
  }

  toggleLoading() {
    this.loading = !this.loading;
  }

  ngOnDestroy(): void {
    if (this.serviceSubscription) {
      this.serviceSubscription.unsubscribe();
    }
  }
}
