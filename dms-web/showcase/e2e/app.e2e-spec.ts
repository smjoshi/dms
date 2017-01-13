import { ShowcasePage } from './app.po';

describe('showcase App', function() {
  let page: ShowcasePage;

  beforeEach(() => {
    page = new ShowcasePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
