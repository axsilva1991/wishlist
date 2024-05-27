package br.com.axsilva.marketplace.wishlist.web.openapi;

import br.com.axsilva.marketplace.wishlist.web.dto.InsertProductReqWeb;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ErrorResWebDto;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ListProductsResWebDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface WishListOpenApi {

    @Operation(tags = "Api - WishList v1.0.0",
            summary = "Endpoint responsible for insert products on WishList.",
            description = "This endpoint must be used to insert data into the customer's wishlist, with a product limit of 20 products."
    )

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product insertion completed successfully.",
                    content = @Content(mediaType = "application/json"),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An internal error occurred while running the microservice.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Error in business rule validation.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The request was malformed, omitting mandatory attributes.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            )
    }
    )

    ResponseEntity<HttpStatus> insertProduct(
            @Parameter(description = "Identification code of client", required = true) @Valid @NotNull(message = "Required field")
            @PathVariable("clientId") String clientId,
            @Parameter(name = "Request body", description = "Payload for insert product on wishlist.", required = true)
            @RequestBody @Valid InsertProductReqWeb insertProductReqWeb);

    @Operation(tags = "Api - WishList v1.0.0",
            summary = "Endpoint responsible to list all products on WishList by clientId.",
            description = "This endpoint must be used to query data on the customer's wish list, resulting in a list of products of up to 20 items."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Wishlist data obtained successfully.",
                    content = @Content(mediaType = "application/json"),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The requested resource does not exists.",
                    content = @Content(mediaType = "application/json"),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An internal error occurred while running the microservice.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The request was malformed, omitting mandatory attributes.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            )
    }
    )

    ResponseEntity<ListProductsResWebDto> getProductsBy(
            @Parameter(description = "Identification code of client", required = true) @Valid @NotNull(message = "Required field")
            @PathVariable("clientId") String clientId);


    @Operation(tags = "Api - WishList v1.0.0",
            summary = "Endpoint responsible for remove products on a customer's wish list.",
            description = "This endpoint must be used to remove data into the customer's wishlist."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Product deleted from Wishlist.",
                    content = @Content(mediaType = "application/json"),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product is not found.",
                    content = @Content(mediaType = "application/json"),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An internal error occurred while running the microservice.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Error in business rule validation.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The request was malformed, omitting mandatory attributes.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            )
    }
    )

    public ResponseEntity<HttpStatus> deleteProductBy(
            @Parameter(description = "Identification code of client", required = true) @Valid @NotNull(message = "Required field")
            @PathVariable("clientId") String clientId,
            @Parameter(description = "Identification code of product selected", required = true) @Valid @NotNull(message = "Required field")
            @RequestParam("productId") String productReferenceCode);



    @Operation(tags = "Api - WishList v1.0.0",
            summary = "Check if a product is selected on Wishlist of the client.",
            description = "This endpoint must be used to verify if a product was selected by client."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product has been selected on Wishlist.",
                    content = @Content(mediaType = "application/json"),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product hasn't been  selected on Wishlist.",
                    content = @Content(mediaType = "application/json"),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An internal error occurred while running the microservice.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The request was malformed, omitting mandatory attributes.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ErrorResWebDto.class)),
                            examples = {
                                    @ExampleObject(
                                            "  {\n" +
                                                    "    \"error\":" +
                                                    "      {\n" +
                                                    "        \"code\": \"ERROR_CODE\",\n" +
                                                    "        \"title\": \"error code title\"\n" +
                                                    "      }\n" +
                                                    "  }\n"
                                    )}),
                    headers = {@Header(name = "correlation-id", description = "Correlation identification, provided to help with call tracking and support.", example = "66525b40acaf11ccd874212f03585c29")}
            )
    }
    )
    ResponseEntity<HttpStatus> checkIfIsOnBy(
            @Parameter(description = "Identification code of client", required = true) @Valid @NotNull(message = "Required field")
            @RequestParam("clientId") String clientId,
            @Parameter(description = "Identification code of product selected", required = true) @Valid @NotNull(message = "Required field")
            @RequestParam("productId") String productReferenceCode);

}
