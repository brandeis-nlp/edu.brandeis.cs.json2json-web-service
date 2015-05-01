html{
    "@xmlns:bar""http://www.bar.org"
    "@xmlns:foo""http://www.foo.org/"
    body {
        h2  {
                "@style" "font:12pt"
                "__text__" "My CD Collection"
            }
        table {
            "@border" "1"
            tr (
                [{
                    "@bgcolor" "#9acd32"
                    th "Title", "Artist", "Country", "Company", "Price", "Year"
                }] +
                &$cd.select{&.price.text() > "9"}.foreach {
                    [td : [&.title#text, &.artist.text(), &.company.text(), &.country.text(), &.price.text(),  &.year.text()]]
                }
            )
        }
    }
}