var dogBreed = Array("Doberman", "Yorkshire Terrier", "Dachshund",
    "Scottish Terrier", "Great Dane", "Portuguese Water Dog")

var filteredDogBreed = for
{
    breed <- dogBreed
    if breed.contains("Terrier") && !breed.startsWith("Yorkshire")
} yield breed